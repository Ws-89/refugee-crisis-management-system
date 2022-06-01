package com.example.demo.models.productsdelivery;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.sql.*;
import java.util.Objects;

public class ProductDeliveryStateType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return ProductDeliveryState.class;
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
                return new ProductDeliveryStateCanceled();
            case "Finished":
                return new ProductDeliveryStateFinished();
            case "InTransition":
                return new ProductDeliveryStateInTransition();
            case "Prepared":
                return new ProductDeliveryStatePrepared();
            case "Rejected":
                return new ProductDeliveryStateRejected();
            case "Started":
                return new ProductDeliveryStateStarted();
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
            ProductDeliveryState state = (ProductDeliveryState) o;
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
