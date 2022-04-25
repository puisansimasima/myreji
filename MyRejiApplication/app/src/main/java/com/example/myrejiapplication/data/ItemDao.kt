package com.example.myrejiapplication.data


import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.myrejiapplication.data.Item

@Dao
interface ItemDao {
    //名前で昇順並び替え
    @Query("SELECT * from item ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>

   // @Query("SELECT * from Item WHERE Id=Id")
    //fun getItem(itemId: Int): Flow<Item>
    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT*FROM Item")
    fun getAll():Flow<List<Item>>




}