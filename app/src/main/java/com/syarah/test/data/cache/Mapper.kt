package com.syarah.test.data.cache

interface Mapper<I, O>  {
    suspend fun mapTo(input: I?): O
/*    suspend fun mapFrom(singleInput: O): I
    suspend fun mapTo(inputList: List<I?>): List<O> {
        return inputList.map {
            mapTo(it)
        }
    }*/

  /*  suspend fun mapFrom(inputList: List<O>): List<I> {
        return inputList.map {
            mapFrom(it)
        }
    }*/
}