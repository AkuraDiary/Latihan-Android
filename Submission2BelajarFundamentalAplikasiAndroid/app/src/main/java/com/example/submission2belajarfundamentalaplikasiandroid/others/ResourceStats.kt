package com.example.submission2belajarfundamentalaplikasiandroid.others


class ResourceStats<out E>(val states : myStates, val data : E?, val message : String?) {
    companion object{

        fun <E> onLoading(data: E?): ResourceStats<E> = ResourceStats(myStates.IS_LOADING, data, null)

        fun <E> onSuccess(data: E): ResourceStats<E> = ResourceStats(myStates.IS_SUCCESS,data, null )

        fun <E> onError(data: E?, message:String): ResourceStats<E> = ResourceStats(myStates.IS_ERROR, data, message)

    }
}