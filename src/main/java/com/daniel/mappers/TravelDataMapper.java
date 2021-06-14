package com.daniel.mappers;

import com.daniel.domain.TravelData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TravelDataMapper {
    
    
    @Select("select count(*) from travel_data where traveltime = #{traveltime} ")
    int getTravelCount(String traveltime);
    
    @Select("select * from travel_data")
    List<TravelData> selectTravelList();
    
    /**
     * delete by primary key
     * @param empid primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(@Param("empid") Integer empid, @Param("traveltime") String traveltime);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(TravelData record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(TravelData record);

    /**
     * select by primary key
     * @param empid primary key
     * @return object by primary key
     */
    TravelData selectByPrimaryKey(@Param("empid") Integer empid, @Param("traveltime") String traveltime);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(TravelData record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(TravelData record);
}