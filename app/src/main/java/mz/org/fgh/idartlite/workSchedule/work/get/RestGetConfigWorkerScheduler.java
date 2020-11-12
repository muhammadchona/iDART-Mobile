package mz.org.fgh.idartlite.workSchedule.work.get;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import mz.org.fgh.idartlite.base.service.BaseService;
import mz.org.fgh.idartlite.rest.helper.RESTServiceHandler;
import mz.org.fgh.idartlite.rest.service.RestDiseaseTypeService;
import mz.org.fgh.idartlite.rest.service.RestDispenseTypeService;
import mz.org.fgh.idartlite.rest.service.RestDrugService;
import mz.org.fgh.idartlite.rest.service.RestFormService;
import mz.org.fgh.idartlite.rest.service.RestPharmacyTypeService;
import mz.org.fgh.idartlite.rest.service.RestTherapeuticLineService;
import mz.org.fgh.idartlite.rest.service.RestTherapeuticRegimenService;

public class RestGetConfigWorkerScheduler extends Worker {

    private static final String TAG = "WorkerScheduler";

    public RestGetConfigWorkerScheduler(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            if (RESTServiceHandler.getServerStatus(BaseService.baseUrl)) {
                Log.d(TAG, "doWork: Sync data Configuration");
                RestDispenseTypeService.restGetAllDispenseType();
                RestPharmacyTypeService.restGetAllPharmacyType();
                RestFormService.restGetAllForms();
                RestDiseaseTypeService.restGetAllDiseaseType();
                RestDrugService.restGetAllDrugs();
                RestTherapeuticRegimenService.restGetAllTherapeuticRegimen();
                RestTherapeuticLineService.restGetAllTherapeuticLine();
            } else {
                Log.e(TAG, "Response Servidor Offline");
                return Result.failure();

            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure();
        }

        return Result.success();
    }

}
