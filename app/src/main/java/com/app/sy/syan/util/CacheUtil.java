package com.app.sy.syan.util;

import android.content.Context;

public class CacheUtil {
    private static Context mContext;
//    private static DaoSession mDaoSession;
//
//    private static class SingletonHolder {
//        private static CacheUtil INSTANCE = new CacheUtil(mContext,mDaoSession);
//    }
//
//    public static CacheUtil getInstance(Context context,DaoSession daoSession){
//        if (context != null) {
//            mContext = context;
//        }
//        if(daoSession != null) {
//            mDaoSession = daoSession;
//        }
//        return SingletonHolder.INSTANCE;
//    }
//
//    public CacheUtil(Context context,DaoSession daoSession) {
//        this.mContext = context;
//        this.mDaoSession = daoSession;
//    }
//
//    public interface CacheCallBack<T> {
//        void cacheData(T bean);
//    }

    /**
     * 保存
     * @param key
     * @param value
     */
//    public void saveCache(String key,String value){
//        DataCacheBean bean = new DataCacheBean();
//        bean.setKey(key);
//        bean.setJsonStr(value);
//        long l = mDaoSession.getDataCacheBeanDao().insertOrReplace(bean);
//    }
//
//    /**
//     * 查询
//     * @param key
//     * @return
//     */
//    public String query(String key){
//        DataCacheBeanDao dataCacheBeanDao = mDaoSession.getDataCacheBeanDao();
//        DataCacheBean cacheBean = dataCacheBeanDao.queryBuilder()
//                .where(DataCacheBeanDao.Properties.Key.eq(key)).unique();
//        if(cacheBean != null) {
//            return cacheBean.getJsonStr();
//        }
//        return "";
//    }
//
//    /**
//     * 删除一个
//     */
//    public void deleteCache(String key){
//        DataCacheBeanDao dataCacheBeanDao = mDaoSession.getDataCacheBeanDao();
//        dataCacheBeanDao.queryBuilder().where(DataCacheBeanDao.Properties.Key.eq(key)).buildDelete().executeDeleteWithoutDetachingEntities();
////        dataCacheBeanDao.deleteByKey(key);
//    }
//
//    /**
//     * 删除全部缓存
//     */
//    public void deleteAllCache(){
//        DataCacheBeanDao dataCacheBeanDao = mDaoSession.getDataCacheBeanDao();
//        dataCacheBeanDao.deleteAll();
//    }
}
