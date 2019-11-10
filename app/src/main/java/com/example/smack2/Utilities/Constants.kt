package com.example.smack2.Utilities

//this is for online database
const val BASE_URL = "https://chattychatcep.herokuapp.com/v1/"

//this is for local host data base
//const val BASE_URL = "http://10.0.2.2:3005/v1/"

//URLs
const val SOCKET_URL = "https://chattychatcep.herokuapp.com/"
const val URL_REGISTER = "${BASE_URL}account/register"
const val URL_LOGIN = "${BASE_URL}account/login"
const val URL_CREATE_USER = "${BASE_URL}user/add"
const val URL_GET_USER = "${BASE_URL}user/byEmail/"
const val URL_GET_CHANNELS = "${BASE_URL}channel/"
//broadcast constants

const val BROADCAST_USER_DATA_CHANGE = "BROADCAST_USER_DATA_CHANGE"

