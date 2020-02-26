package com.thrymr.formersmarket.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.thrymr.formersmarket.Model.ConsumerRegistration;
import com.thrymr.formersmarket.Repositories.ConsumerRegRepository;

import java.util.List;

public class ConsumerRegViewModel extends AndroidViewModel {

    private ConsumerRegRepository regRepository;
    private LiveData<List<ConsumerRegistration>> allRegistrations;


    public ConsumerRegViewModel(@NonNull Application application) {
        super(application);
        regRepository=new ConsumerRegRepository(application);
        allRegistrations=regRepository.getAllRegistrations();
    }

    public void insert(ConsumerRegistration registration){
        regRepository.insert(registration);
    }
    public void update(ConsumerRegistration registration){
        regRepository.update(registration);
    }
    public void delete(ConsumerRegistration registration){
        regRepository.delete(registration);
    }
    public void deleteAll(){
        regRepository.deleteAll();
    }

    public LiveData<List<ConsumerRegistration>> getAllRegistrations() {return allRegistrations;}
}
