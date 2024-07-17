package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDao;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl  implements SupplierDao {
    @Override
    public  boolean delete(String supplierId) throws SQLException {
        return SQLUtil.execute("DELETE FROM Suppliers WHERE supplierId = ?"  , supplierId);

    }
    @Override
    public  boolean save(Supplier supplier) throws SQLException {
        return SQLUtil.execute("INSERT INTO Suppliers VALUES(?, ?, ?, ? , ? ,? )" , supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getSpecialCar(), supplier.getSpecialCarCost(), supplier.getVehicleId());
    }
    @Override
    public  boolean update(Supplier supplier) throws SQLException {
        return SQLUtil.execute("UPDATE Suppliers SET name = ? , contact = ? , specialCar = ? , specialCarCost = ? , vehicleId = ?  WHERE supplierId = ?", supplier.getName(), supplier.getContact(), supplier.getSpecialCar(), supplier.getSpecialCarCost(), supplier.getVehicleId(), supplier.getSupplierId());

    }
    @Override
    public  Supplier searchById(String supplierId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Suppliers WHERE supplierId  = ?" , supplierId + "");
        if (resultSet.next()){
              Supplier supplier = new Supplier(supplierId + "" , resultSet.getString("name"), resultSet.getString("contact"), resultSet.getString("specialCar"), resultSet.getDouble("specialCarCost"), resultSet.getString("vehicleId"));
              return supplier;
        }
        return null;
    }

    @Override
    public Supplier searchByName(String name) throws SQLException {
        return null;
    }

    @Override
    public List<String> getIds() throws SQLException {
        return null;
    }

    @Override
    public List<Supplier> getAllDesc() throws SQLException {
        return null;
    }

    @Override
    public List<String> getNameDetails() throws SQLException {
        return null;
    }

    @Override
    public List<Supplier> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Suppliers");
        ArrayList<Supplier> arrayList = new ArrayList<>();

        while (resultSet.next()){
              arrayList.add(new Supplier(
                      resultSet.getString("supplierId"),
                      resultSet.getString("name"),
                      resultSet.getString("contact"),
                      resultSet.getString("specialCar"),
                      resultSet.getDouble("specialCarCost"),
                      resultSet.getString("vehicleId")
              ));
        }
        return arrayList ;
    }
}
