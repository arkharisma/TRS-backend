package com.finalproject.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.finalproject.backend.payload.response.TripClientResponse;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationRepository {

    @PersistenceContext
    private EntityManager em;

    public List<TripClientResponse> findTripScheduleAllIndicator(String sourceStopId, String destStopId, String tripDate){
        Query query = em.createNativeQuery("SELECT DISTINCT a.id AS id, a.trip_date AS trip_date, a.available_seats AS available_seats, b.fare AS fare, b.journey_time AS journey_time, d.name AS agency_name, c.code AS bus_code FROM trip_schedule a JOIN trip b ON a.trip_detail_id = b.id JOIN bus c ON b.bus_id = c.id JOIN agency d ON b.agency_id = d.id WHERE b.source_stop_id = '" + sourceStopId + "' AND b.dest_stop_id = '" + destStopId + "' AND a.trip_date = '" + tripDate + "'");

        query.unwrap(SQLQuery.class)
            .addScalar("id", StringType.INSTANCE)
            .addScalar("trip_date", StringType.INSTANCE)
            .addScalar("available_seats", StringType.INSTANCE)
            .addScalar("fare", IntegerType.INSTANCE)
            .addScalar("journey_time", StringType.INSTANCE)
            .addScalar("agency_name", StringType.INSTANCE)
            .addScalar("bus_code", StringType.INSTANCE)
            .setResultTransformer(Transformers.aliasToBean(TripClientResponse.class));

        List<TripClientResponse> items = query.getResultList();
        return items;
    }

    public List<TripClientResponse> findTripScheduleIndicatorStops(String sourceStopId, String destStopId){
        Query query = em.createNativeQuery("SELECT DISTINCT a.id AS id, a.trip_date AS trip_date, a.available_seats AS available_seats, b.fare AS fare, b.journey_time AS journey_time, d.name AS agency_name, c.code AS bus_code FROM trip_schedule a JOIN trip b ON a.trip_detail_id = b.id JOIN bus c ON b.bus_id = c.id JOIN agency d ON b.agency_id = d.id WHERE b.source_stop_id = '" + sourceStopId + "' AND b.dest_stop_id = '" + destStopId + "' AND STR_TO_DATE(a.trip_date, '%d/%m/%Y') >= NOW()");

        query.unwrap(SQLQuery.class)
            .addScalar("id", StringType.INSTANCE)
            .addScalar("trip_date", StringType.INSTANCE)
            .addScalar("available_seats", StringType.INSTANCE)
            .addScalar("fare", IntegerType.INSTANCE)
            .addScalar("journey_time", StringType.INSTANCE)
            .addScalar("agency_name", StringType.INSTANCE)
            .addScalar("bus_code", StringType.INSTANCE)
            .setResultTransformer(Transformers.aliasToBean(TripClientResponse.class));

        List<TripClientResponse> items = query.getResultList();
        return items;
    }

    public TripClientResponse getTripScheduleDetail(String id){
        Query query = em.createNativeQuery("SELECT DISTINCT a.id AS id, a.trip_date AS trip_date, a.available_seats AS available_seats, b.fare AS fare, b.journey_time AS journey_time, d.name AS agency_name, c.code AS bus_code FROM trip_schedule a JOIN trip b ON a.trip_detail_id = b.id JOIN bus c ON b.bus_id = c.id JOIN agency d ON b.agency_id = d.id WHERE a.id = '" + id + "'");

        query.unwrap(SQLQuery.class)
            .addScalar("id", StringType.INSTANCE)
            .addScalar("trip_date", StringType.INSTANCE)
            .addScalar("available_seats", StringType.INSTANCE)
            .addScalar("fare", IntegerType.INSTANCE)
            .addScalar("journey_time", StringType.INSTANCE)
            .addScalar("agency_name", StringType.INSTANCE)
            .addScalar("bus_code", StringType.INSTANCE)
            .setResultTransformer(Transformers.aliasToBean(TripClientResponse.class));

        TripClientResponse items = (TripClientResponse) query.getResultList().get(0);
        return items;
    }
}
