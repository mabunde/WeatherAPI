package com.example.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("weather/")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public ResponseEntity<?> addWeatherRecord(@RequestBody Weather weather){
        return weatherService.createWeatherData(weather);
    }
    @GetMapping
    public ResponseEntity<?> getAllWeatherRecord(){
        return  weatherService.getAllWeatherRecord();
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getWeatherById(@PathVariable Long id){
        return  weatherService.getWeatherRecordById(id);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteWeatherById(@PathVariable Long id){
        return weatherService.deleteWeatherRecord(id);
    }
//    @PutMapping("{id}")
//    public ResponseEntity<?> updateWeather(@RequestBody Weather weather,@PathVariable Long id){
//
//    }
}
