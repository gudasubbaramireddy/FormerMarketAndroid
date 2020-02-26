package com.thrymr.formersmarket.Repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.thrymr.formersmarket.Daos.ConsumerRegDao;
import com.thrymr.formersmarket.Database.FarmersMarketDatabase;
import com.thrymr.formersmarket.Model.ConsumerRegistration;

import java.util.List;

public class ConsumerRegRepository  {

    private ConsumerRegDao regDao;
    private LiveData<List<ConsumerRegistration>> listLiveData;

    public ConsumerRegRepository(Application application){
        FarmersMarketDatabase database= FarmersMarketDatabase.getInstance(application);
        regDao=database.consumerRegDao();
        listLiveData=regDao.getAllRegistrations();
    }
    public void insert(ConsumerRegistration registration) {
        new InsertConsumerRegAsyncTask(regDao).execute(registration);
    }
    public void update(ConsumerRegistration registration) {
        new UpdateConsumerRegAsyncTask(regDao).execute(registration);
    }
    public void delete(ConsumerRegistration registration) {
        new DeleteConsumerRegAsyncTask(regDao).execute(registration);
    }
    public void deleteAll() {
        new DeleteAllConsumerRegAsyncTask(regDao).execute();
    }
    public LiveData<List<ConsumerRegistration>> getAllRegistrations() {
        return listLiveData;
    }

    private static class InsertConsumerRegAsyncTask extends AsyncTask<ConsumerRegistration,Void,Void> {

        private ConsumerRegDao regDao;

        private InsertConsumerRegAsyncTask(ConsumerRegDao regDao){
            this.regDao=regDao;
        }
        @Override
        protected Void doInBackground(ConsumerRegistration... consumerRegistrations) {
            regDao.insert(consumerRegistrations[0]);
            return null;
        }
    }

    private static class UpdateConsumerRegAsyncTask extends AsyncTask<ConsumerRegistration,Void,Void> {

        private ConsumerRegDao regDao;

        private UpdateConsumerRegAsyncTask(ConsumerRegDao regDao){
            this.regDao=regDao;
        }
        @Override
        protected Void doInBackground(ConsumerRegistration... consumerRegistrations) {
            regDao.update(consumerRegistrations[0]);
            return null;
        }
    }

    private static class DeleteConsumerRegAsyncTask extends AsyncTask<ConsumerRegistration,Void,Void> {

        private ConsumerRegDao regDao;

        private DeleteConsumerRegAsyncTask(ConsumerRegDao regDao){
            this.regDao=regDao;
        }
        @Override
        protected Void doInBackground(ConsumerRegistration... consumerRegistrations) {
            regDao.delete(consumerRegistrations[0]);
            return null;
        }
    }

    private static class DeleteAllConsumerRegAsyncTask extends AsyncTask<Void,Void,Void> {

        private ConsumerRegDao regDao;

        private DeleteAllConsumerRegAsyncTask(ConsumerRegDao regDao){
            this.regDao=regDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            regDao.deleteAll();
            return null;
        }
    }
}
