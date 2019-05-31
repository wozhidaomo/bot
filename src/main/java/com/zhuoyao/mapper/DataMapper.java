package com.zhuoyao.mapper;

import com.zhuoyao.model.Yaoling;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DataMapper {


    @Select("SELECT * FROM yaoling")
    @Results({
            @Result(property = "gentime",  column = "gentime"),
            @Result(property = "latitude", column = "latitude"),
            @Result(property = "lifetime", column = "lifetime"),
            @Result(property = "longtitude", column = "longtitude"),
            @Result(property = "sprite_id", column = "sprite_id")
    })
    List<Yaoling> getAll();

    @Select("SELECT * FROM yaoling WHERE id = #{id}")
    @Results({
            @Result(property = "gentime",  column = "gentime"),
            @Result(property = "latitude", column = "latitude"),
            @Result(property = "lifetime", column = "lifetime"),
            @Result(property = "longtitude", column = "longtitude"),
            @Result(property = "sprite_id", column = "sprite_id")
    })
    Yaoling getOne(Long id);

//    @Insert("INSERT INTO yaoling(gentime,latitude,lifetime,longtitude,sprite_id) VALUES(#{gentime}, #{latitude}, #{lifetime}, #{longtitude}, #{sprite_id})")
    @Insert({"<script>",
            "insert into yaoling(gentime,latitude,lifetime,longtitude,sprite_id) VALUES",
            "<foreach collection='ys' item='i' index='index' separator=','>",
            "(#{i.gentime}, #{i.latitude}, #{i.lifetime}, #{i.longtitude}, #{i.sprite_id})",
            "</foreach>",
            "ON DUPLICATE KEY UPDATE ",
            "gentime=VALUES(gentime), latitude=VALUES(latitude), lifetime=VALUES(lifetime),longtitude= VALUES(longtitude), sprite_id=VALUES(sprite_id)",
            "</script>"

    })
    int inserts(@Param(value="ys")List<Yaoling> ys);




}
