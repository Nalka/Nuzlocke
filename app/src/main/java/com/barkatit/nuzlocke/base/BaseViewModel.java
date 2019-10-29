package com.barkatit.nuzlocke.base;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.barkatit.nuzlocke.data.repository.Repository;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;


public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposable;

    public Repository repository;

    public BaseViewModel(Repository repository) {
        this.repository = repository;
        disposable = new CompositeDisposable();
    }


    protected Repository getRepository() {
        return repository;
    }

    protected void execute(Completable completable, DisposableCompletableObserver observer) {
        disposable.add(completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    protected void execute(Flowable<?> flowable, ResourceSubscriber observer) {
        disposable.add(flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer)
        );
    }

    protected void execute(Single<?> single, DisposableSingleObserver observer) {
        disposable.add(single
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
