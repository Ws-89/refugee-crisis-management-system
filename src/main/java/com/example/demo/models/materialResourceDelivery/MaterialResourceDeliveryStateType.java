package com.example.demo.models.materialResourceDelivery;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.*;
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
        return ObjectUtils.nullSafeEquals(o, o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        assert (o != null);
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        String name = resultSet.getString(strings[0]);
        if(resultSet.wasNull())
            return null;

        switch (name){
            case "Canceled":
                return new MaterialResourceDeliveryStateCanceled();
            case "Finished":
                return new MaterialResourceDeliveryStateFinished();
            case "InTransition":
                return new MaterialResourceDeliveryStateInTransition();
            case "Prepared":
                return new MaterialResourceDeliveryStatePrepared();
            case "Rejected":
                return new MaterialResourceDeliveryStateRejected();
            case "Started":
                return new MaterialResourceDeliveryStateStarted();
            default:
                throw new RuntimeException("State corrupted");

        }

    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if(Objects.isNull(o)) {
            preparedStatement.setNull(i, Types.VARCHAR);
        }
        else{
            MaterialResourceDeliveryState state = (MaterialResourceDeliveryState) o;
            preparedStatement.setString(i, state.getName());
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Serializable)o;
    }

    @Override
    public Object assemble(Serializable serializable, Object o) throws HibernateException {
        return serializable;
    }

    @Override
    public Object replace(Object o, Object o1, Object o2) throws HibernateException {
        return o;
    }
}
