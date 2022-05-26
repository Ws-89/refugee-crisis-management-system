package com.example.demo.models.materialResourceDelivery;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;

public class MaterialResourceDeliveryStateType implements UserType {
    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return MaterialResourceDeliveryState.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return false;
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return 0;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String name = resultSet.getString(strings[0]);
        if(resultSet.wasNull())
            return null;

        switch (name){
            case MaterialResourceDeliveryStateCanceled.stateName:
                return new MaterialResourceDeliveryStateCanceled();
            case MaterialResourceDeliveryStateFinished.stateName:
                return new MaterialResourceDeliveryStateCanceled();
            case MaterialResourceDeliveryStateInTransition.stateName:
                return new MaterialResourceDeliveryStateInTransition();
            case MaterialResourceDeliveryStatePrepared.stateName:
                return new MaterialResourceDeliveryStatePrepared();
            case MaterialResourceDeliveryStateRejected.stateName:
                return new MaterialResourceDeliveryStateRejected();
            case MaterialResourceDeliveryStateStarted.stateName:
                return new MaterialResourceDeliveryStateStarted();
            default:
                throw new RuntimeException("State corrupted");

        }

    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if(Objects.isNull(o)) {
            throw new IllegalArgumentException("State field is empty");
        }

        MaterialResourceDeliveryState state = (MaterialResourceDeliveryState) o;
        preparedStatement.setString(i, state.getName());
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return null;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return null;
    }
}
