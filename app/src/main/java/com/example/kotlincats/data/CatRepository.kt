package com.example.kotlincats.data

import com.example.kotlincats.data.api.ApiDataSource
import com.example.kotlincats.data.database.CatDao
import com.example.kotlincats.domain.model.Cat
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catDao: CatDao,
    private val apiDataSource: ApiDataSource
) {

    suspend fun getCats(): List<Cat> {
        var cats: List<Cat> = catDao.getCats()
        // FIXME: DB is not populated here yet after storage clean.

        if (cats.isEmpty()) {
            cats = apiDataSource.getCats(30)
            insert(cats)
        }

        return cats
    }


    suspend fun insert(cat: Cat) = catDao.insert(cat)


    suspend fun insert(cats: List<Cat>) = catDao.insert(cats)


    suspend fun delete(cat: Cat) = catDao.delete(cat)
}