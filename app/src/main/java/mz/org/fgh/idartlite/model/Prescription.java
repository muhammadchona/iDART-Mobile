package mz.org.fgh.idartlite.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import mz.org.fgh.idartlite.base.BaseModel;
import mz.org.fgh.idartlite.dao.PrescriptionDaoImpl;
import mz.org.fgh.idartlite.util.DateUtilitis;
import mz.org.fgh.idartlite.util.Utilities;

@DatabaseTable(tableName = "prescription", daoClass = PrescriptionDaoImpl.class)
public class Prescription extends BaseModel {

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_PRESCRIPTION_DATE = "prescription_date";
	public static final String COLUMN_SUPPLY = "supply";
	public static final String COLUMN_EXPIRY_DATE = "expiry_date";
	public static final String COLUMN_URGENT_PRESCRIPTION = "urgent_prescription";
	public static final String COLUMN_URGENT_NOTES = "urgent_notes";
	public static final String COLUMN_REGIMEN_ID = "regimen_id";
	public static final String COLUMN_LINE_ID = "line_id";
	public static final String COLUMN_DISPENSE_TYPE_ID = "dispense_type_id";
	public static final String COLUMN_PRESCRIPTION_SEQ = "prescription_seq";
	public static final String COLUMN_UUID = "uuid";
	public static final String COLUMN_PATIENT_ID = "patient_id";
	public static final String COLUMN_SYNC_STATUS = "sync_status";

	public static final String DURATION_TWO_WEEKS = "2 Semanas";
	public static final String DURATION_ONE_MONTH = "1 Mês";
	public static final String DURATION_TWO_MONTHS = "2 Meses";
	public static final String DURATION_THREE_MONTHS = "3 Meses";
	public static final String DURATION_FOUR_MONTHS = "4 Meses";
	public static final String DURATION_FIVE_MONTHS = "5 Meses";
	public static final String DURATION_SIX_MONTHS = "6 Meses";

	public static final String URGENT_PRESCRIPTION = "Especial";
	public static final String NOT_URGENT_PRESCRIPTION = "Não Especial";

	@DatabaseField(columnName = COLUMN_ID, generatedId = true)
	private int id;

	@DatabaseField(columnName = COLUMN_PRESCRIPTION_DATE)
	private Date prescriptionDate;

	@DatabaseField(columnName = COLUMN_SUPPLY)
	private int supply;

	@DatabaseField(columnName = COLUMN_EXPIRY_DATE)
	private Date expiryDate;

	@DatabaseField(columnName = COLUMN_URGENT_PRESCRIPTION)
	private String urgentPrescription;

	@DatabaseField(columnName = COLUMN_URGENT_NOTES)
	private String urgentNotes;

	@DatabaseField(columnName = COLUMN_REGIMEN_ID, canBeNull = true, foreign = true, foreignAutoRefresh = true)
	private TherapeuticRegimen therapeuticRegimen;

	@DatabaseField(columnName = COLUMN_LINE_ID, canBeNull = true, foreign = true, foreignAutoRefresh = true)
	private TherapeuticLine therapeuticLine;

	@DatabaseField(columnName = COLUMN_DISPENSE_TYPE_ID, canBeNull = true, foreign = true, foreignAutoRefresh = true)
	private DispenseType dispenseType;

	@DatabaseField(columnName = COLUMN_PRESCRIPTION_SEQ)
	private String prescriptionSeq;

	@DatabaseField(columnName = COLUMN_UUID)
	private String uuid;

	@DatabaseField(columnName = COLUMN_PATIENT_ID, canBeNull = false, foreign = true, foreignAutoRefresh = true)
	private Patient patient;

	@DatabaseField(columnName = COLUMN_SYNC_STATUS)
	private String syncStatus;

	private List<PrescribedDrug> prescribedDrugs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPrescriptionDate() {
		return prescriptionDate;
	}

	public void setPrescriptionDate(Date prescriptionDate) {
		this.prescriptionDate = prescriptionDate;
	}

	public int getSupply() {
		return supply;
	}

	public void setSupply(int supply) {
		this.supply = supply;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUrgentPrescription() {
		return urgentPrescription;
	}

	public void setUrgentPrescription(String urgentPrescription) {
		this.urgentPrescription = urgentPrescription;
	}

	public String getUrgentNotes() {
		return urgentNotes;
	}

	public void setUrgentNotes(String urgentNotes) {
		this.urgentNotes = urgentNotes;
	}

	public TherapeuticRegimen getTherapeuticRegimen() {
		return therapeuticRegimen;
	}

	public void setTherapeuticRegimen(TherapeuticRegimen therapeuticRegimen) {
		this.therapeuticRegimen = therapeuticRegimen;
	}

	public TherapeuticLine getTherapeuticLine() {
		return therapeuticLine;
	}

	public void setTherapeuticLine(TherapeuticLine therapeuticLine) {
		this.therapeuticLine = therapeuticLine;
	}

	public String getPrescriptionSeq() {
		return prescriptionSeq;
	}

	public void setPrescriptionSeq(String prescriptionSeq) {
		this.prescriptionSeq = prescriptionSeq;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public DispenseType getDispenseType() {
		return dispenseType;
	}

	public void setDispenseType(DispenseType dispenseType) {
		this.dispenseType = dispenseType;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(String syncStatus) {
		this.syncStatus = syncStatus;
	}

	public List<PrescribedDrug> getPrescribedDrugs() {
		return prescribedDrugs;
	}

	public void setPrescribedDrugs(List<PrescribedDrug> prescribedDrugs) {
		this.prescribedDrugs = prescribedDrugs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Prescription that = (Prescription) o;
		return prescriptionDate.equals(that.prescriptionDate) &&
				expiryDate.equals(that.expiryDate) &&
				prescriptionSeq.equals(that.prescriptionSeq) &&
				uuid.equals(that.uuid);
	}

	public double getDurationInMonths(){
		if (expiryDate == null) return  0;
		return DateUtilitis.dateDiff(this.prescriptionDate, this.expiryDate, DateUtilitis.MONTH_FORMAT);
	}

	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	public int hashCode() {
		return Objects.hash(prescriptionDate, expiryDate, prescriptionSeq, uuid);
	}

	@Override
	public String toString() {
		return "Prescription{" +
				"prescriptionDate=" + prescriptionDate +
				", supply=" + supply +
				", expiryDate=" + expiryDate +
				", urgentPrescription=" + urgentPrescription +
				", urgentNotes='" + urgentNotes + '\'' +
				", dispenseType=" + dispenseType +
				", prescriptionSeq='" + prescriptionSeq + '\'' +
				", uuid='" + uuid + '\'' +
				", syncStatus='" + syncStatus + '\'' +
				'}';
	}

	public boolean isUrgent(){
		return  this.urgentPrescription.equalsIgnoreCase(URGENT_PRESCRIPTION);
	}

	@RequiresApi(api = Build.VERSION_CODES.O)
	public String getDuration(Date pickupDate, Date nextPickupDate){
		//Parsing the date
		LocalDate pickupLocalDate = pickupDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

		LocalDate nextPickupLocalDate  = nextPickupDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDate();

		//calculating number of days in between
		long noOfDaysBetween = ChronoUnit.DAYS.between(pickupLocalDate, nextPickupLocalDate);

		return String.valueOf(noOfDaysBetween);

	}

    public String validate() {
		if (this.prescriptionDate == null) return "A data da prescrição é obrigatória";
		if(DateUtilitis.dateDiff(this.prescriptionDate, DateUtilitis.getCurrentDate(), DateUtilitis.DAY_FORMAT) > 0) {
			return "A data da prescrição não pode ser maior que a data corrente.";
		}
		if(this.supply <= 0) return "A duração da prescrição deve ser indicada.";
		if(this.dispenseType == null || !Utilities.stringHasValue(this.dispenseType.getDescription())) return "O campo tipo de dispensa é obrigatório.";
		if(this.therapeuticRegimen == null || !Utilities.stringHasValue(this.therapeuticRegimen.getDescription())) return "O campo tipo de regime terapeutico é obrigatório.";
		if(this.therapeuticLine == null || !Utilities.stringHasValue(this.therapeuticLine.getDescription())) return "O campo tipo de linha terapeutica é obrigatório.";

		if (!Utilities.listHasElements(this.prescribedDrugs)) return "Por favor indique os medicamentos para esta prescrição";

		if (isUrgent() && !Utilities.stringHasValue(this.urgentNotes)) return "Indicou que a prescrição é especial, por favor indique o motivo.";

		return "";
    }
}
