package com.example.ecommerce.presentation.di

//@Module
//@InstallIn(SingletonComponent::class)
//object DatabaseModule {
//
//
//    @Singleton
//    @Provides
//    fun provideRepository(api: HomeShopApi, homeDao: HomeDao): Repository {
//        return RepositoryImpl(api = api, homeDao = homeDao)
//    }
//
//    @Singleton
//    @Provides
//    fun provideDatabase(@ApplicationContext context: Context) =
//        Room.databaseBuilder(
//            context,
//            HomeShopDatabase::class.java,
//            DATABASE_NAME
//        ).build()
//
//    @Singleton
//    @Provides
//    fun provideHomeDao(shopDatabase: HomeShopDatabase): HomeDao {
//        return shopDatabase.getHomeDao()
//    }
//}