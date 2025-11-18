package com.springboot.bike.controller;

import com.springboot.bike.entities.Bike;
import com.springboot.bike.service.BikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
* @RequestMapping - the annotation is used to map web requests to Spring Controller methods.*/
@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    private BikeService bikeservice;

    // Add a new bike
    @Operation(summary = "Add bike",description = "Inserting bike deatils in DB")
    @ApiResponse(responseCode ="201",description = "Successfully bike deatils are added")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Bike addBike(@RequestBody @Valid Bike bikedata)
    {
        Bike addedbike = bikeservice.addBike(bikedata);
        return addedbike;
    }


    //get bike details by id
    @Operation(summary = "Get the bike by id",description = "Give the id of the bike")
    @ApiResponse(responseCode ="200",description = "Successfully bike details are fetched by giving id")
    @GetMapping(path = "/{id}")
    public Bike getBike(@PathVariable("id") Long Id) {
        Bike fetchedbike= bikeservice.fetchBike(Id);
        return fetchedbike;
    }

    /*
    * fetching all bikes
    * */
    @Operation(summary = "Get all bikes ",description = "Getting all the bike details")
    @ApiResponse(responseCode ="200",description = "Successfully all bike details are fetched ")
    @GetMapping("/allbikes")
    public List<Bike> fetchAllBikes(){
        List<Bike> allBikes=bikeservice.fetchAllBikes();
        return allBikes;
    }


    //Update bike purchase price
    @Operation(summary = "Update the bike purchase price",description = "Get the bike by id and change the purchase price")
    @ApiResponse(responseCode ="200",description = "Successfully bike purchase price is updated")
    @PutMapping("/update")
    public Bike updateBike(  @RequestBody  @Valid Bike bike) {
        return bikeservice.updateBike(bike);
    }

    //deleting employee data by id
    @Operation(summary = "Delete the bike id",description = "Delete the bike deatils by passing its id")
    @ApiResponse(responseCode ="200",description = "Successfully bike details are deleted for the given Id")
    @DeleteMapping(path = "/{id}")
    public String deleteBike(@PathVariable Long id) {
        return bikeservice.deleteBike(id);
    }
}











