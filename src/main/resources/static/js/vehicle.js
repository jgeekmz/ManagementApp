
$('document').ready(function() {	
	$('.table .btn-primary').on('click',function(event){
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicle, status){
			var acDate = vehicle.acquisitionDate.substr(0,10);
			$('#txtAcquisitionDateEdit').val(acDate);
			$('#txtDescriptionEdit').val(vehicle.description);
			$('#ddlEmployeeEdit').val(vehicle.employeeid);
			$('#txtFuelCapacityEdit').val(vehicle.fuelCapacity);
			$('#txtIdEdit').val(vehicle.id);
			$('#ddlLocationEdit').val(vehicle.locationid);
			$('#txtNameEdit').val(vehicle.name);
			$('#txtNetWeightEdit').val(vehicle.netWeight);
			$('#txtPowerEdit').val(vehicle.power);
			var regDate = vehicle.registrationDate.substr(0,10);
			$('#txtRegistrationDateEdit').val(regDate);
			$('#txtRemarksEdit').val(vehicle.remarks);
			$('#ddlVehicleMakeEdit').val(vehicle.vehiclemakeid);		
			$('#ddlVehicleModelEdit').val(vehicle.vehiclemodelid);			
			$('#txtVehicleNumberEdit').val(vehicle.vehicleNumber);			
			$('#ddlVehicleStatusEdit').val(vehicle.vehiclestatusid);			
			$('#ddlVehicleTypeEdit').val(vehicle.vehicletypeid);	
		});			
		$('#editModal').modal();		
	});
	
	$('.table #detailsButton').on('click',function(event) {
		event.preventDefault();		
		var href= $(this).attr('href');		
		$.get(href, function(vehicle, status){
		    $('#txtIdDetails').val(vehicle.id);
		    $('#txtDescriptionDetails').val(vehicle.description);
		    $('#txtNameDetails').val(vehicle.name);
		    $('#txtVehicleNumberDetails').val(vehicle.vehicleNumber);
		    var acDate = vehicle.acquisitionDate.substr(0,10);
			$('#txtAcquisitionDateDetails').val(acDate);
			var regDate = vehicle.registrationDate.substr(0,10);
			$('#txtRegistrationDateDetails').val(regDate);
            $('#txtFuelCapacityDetails').val(vehicle.fuelCapacity);
            $('#txtPowerDetails').val(vehicle.power);
            $('#txtNetWeightDetails').val(vehicle.netWeight);
            $('#txtRemarksDetails').val(vehicle.remarks);
		});			
		$('#detailsModal').modal();		
	});	
	
	$('.table #deleteButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#deleteModal #delRef').attr('href', href);
		$('#deleteModal').modal();		
	});	
	
	$('.table #photoButton').on('click',function(event) {
		event.preventDefault();
		var href = $(this).attr('href');
		$('#photoModal #vehiclePhoto').attr('src', href);
		$('#photoModal').modal();		
	});	
});