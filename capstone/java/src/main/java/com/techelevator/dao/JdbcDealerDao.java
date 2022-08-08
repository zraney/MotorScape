package com.techelevator.dao;

import com.techelevator.model.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcDealerDao implements DealerDao{

    private final JdbcTemplate jdbcTemplate;

    JdbcDealerDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Car> getInventory(){
        List<Car> inventory = new ArrayList<>();
        String sql = "SELECT vin, make, model, year, engine, price, photo" +
                " FROM car;";
        SqlRowSet carRowSet = jdbcTemplate.queryForRowSet(sql);

        while(carRowSet.next()){
            inventory.add(mapRowToCar(carRowSet));
        }

        return inventory;
    }

    private Car mapRowToCar(SqlRowSet carRowSet){
        Car car = new Car();
        car.setVin(carRowSet.getString("vin"));
        car.setMake(carRowSet.getString("make"));
        car.setModel(carRowSet.getString("model"));
        car.setEngine(carRowSet.getString("engine"));
        car.setPhoto(carRowSet.getString("photo"));
        car.setPrice(carRowSet.getDouble("price"));
        car.setYear(carRowSet.getInt("year"));

        return car;
    }
}