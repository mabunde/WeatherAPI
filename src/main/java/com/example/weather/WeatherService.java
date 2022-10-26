package com.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepo weatherRepo;

    public ResponseEntity<?> createWeatherData(Weather weather){
        try {
            Weather saved = weatherRepo.save(weather);
            if(saved !=null){
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new RequestResponse("Success","weather data created successfully")
                );
            }else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new RequestResponse("Error","Error occurred while saving weather data")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RequestResponse("Error",
                            "Error occured while creating weather record. Please try again")
            );
        }
    }
    public ResponseEntity<?> getAllWeatherRecord(){
        try {
            List<Weather> savedList =weatherRepo.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(savedList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RequestResponse("Error",
                            "An error occurred while getting all weather record. Please try again")
            );
        }
    }
    public ResponseEntity<?> getWeatherRecordById(Long id){
        try {
            Weather record= weatherRepo.findById(id).get();
            if(record !=null){
                return ResponseEntity.status(HttpStatus.OK).body(record);
            } else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RequestResponse("Error", "No data with the specified id")
                );
            }
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RequestResponse("Error",
                            "Error while getting weather record")
            );
        }
    }
    public ResponseEntity<?> deleteWeatherRecord(Long id){
        try {
            Weather deleteRecord = weatherRepo.findById(id).get();
            if(deleteRecord!=null){
                weatherRepo.delete(deleteRecord);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new RequestResponse("Success","Weather record deleted successfully")
                );
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new RequestResponse("Error", "Weather with id not found")
                );
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new RequestResponse("Error", "Error while deleting the weather record. Please try again later!")
            );
        }
    }

}
