package com.hysea.rxmodule.entity

/**
 * Created by hysea on 2018/8/7.
 */
data class Hotel(val name: String, val rooms: List<Room>)

data class Room(val name: String, val price: Float)