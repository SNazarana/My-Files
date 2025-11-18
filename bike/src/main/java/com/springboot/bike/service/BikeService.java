package com.springboot.bike.service;

import com.springboot.bike.entities.Bike;
import com.springboot.bike.exception.BikeAlreadyExists;
import com.springboot.bike.exception.BikeNotFoundException;
import com.springboot.bike.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikerepository;

    //add new bike
    public Bike addBike(Bike bike) {
        Bike bikeexists = bikerepository.findById(bike.getBikeId()).orElse(null);
        if (bikeexists == null) {
            Bike addBikeData = bikerepository.save(bike);
            return addBikeData;
        } else
            throw new BikeAlreadyExists("Already there is a bike with this Id");

    }



    //get details of bike by id
    public Bike fetchBike(Long Id) {
        Bike bikefound = bikerepository.findById(Id).orElse(null);
        if (bikefound != null) {
            Bike fetchBikeDatabyId = bikerepository.findById(Id).get();
            return fetchBikeDatabyId;
        } else
            throw new BikeNotFoundException("There is no such bike found with this id");
    }

    /*
    * To fetch all exisiting bike data
    * */
    public List<Bike> fetchAllBikes(){
       List<Bike> allBikes = bikerepository.findAll();
       return allBikes;
    }


    //update bike details
    public Bike updateBike(Bike bike) {
        Bike bikefound = bikerepository.findById(bike.getBikeId()).orElse(null);
        if (bikefound != null) {
            Bike bikedetails = bikerepository.findById(bike.getBikeId()).get();
            bikedetails.setPurchaseprice(bike.getPurchaseprice());
            return bikerepository.save(bikedetails);
        } else
            throw new BikeNotFoundException("There is no such bike found");
    }

    //delete method
    public String deleteBike(Long id) {
        Bike bikefound = bikerepository.findById(id).orElse(null);
        if (bikefound != null) {

            bikerepository.deleteById(id);
            return "Entity deleted " + id;
        } else
            throw new BikeNotFoundException("There is no such bike found");

    }
}









