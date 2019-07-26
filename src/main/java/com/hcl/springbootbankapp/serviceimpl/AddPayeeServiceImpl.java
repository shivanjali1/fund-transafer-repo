package com.hcl.springbootbankapp.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.springbootbankapp.DTO.ResponseDTO;
import com.hcl.springbootbankapp.entity.Payee;
import com.hcl.springbootbankapp.entity.User;
import com.hcl.springbootbankapp.repository.PayeeRepository;
import com.hcl.springbootbankapp.repository.UserRepository;
import com.hcl.springbootbankapp.service.AddPayeeService;
import com.hcl.springbootbankapp.service.OTPService;
import com.hcl.springbootbankapp.util.PayeeStatusUtil;
@Service
public class AddPayeeServiceImpl implements AddPayeeService {

	private final static String bodyInit = " Dear Custemer, Your OTP for the Adding Payee ";
	private final static String bodyMiddle = "with id ";
	private final static String bodyFinal = " and name  ";
	private final static String bodyFinalAgain = " is: ";
	
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PayeeRepository payeeRepository;
	@Autowired
	OTPService oTPService;
	
	/**
	 *@loggedUserId takes logged user id from controller
	 *@payeeUserId takes payee user id from controller
	 *returns returns payee addition request
	 */
	@Override
	public ResponseDTO addPayee(String loggedUserId, String payeeUserId) {
		// TODO Auto-generated method stub
		ResponseDTO responseObject = new ResponseDTO();

		Optional<User> payeeUser = userRepository.findByCustomerId(payeeUserId);
		Optional<User> loggedUser = userRepository.findByCustomerId(loggedUserId);
		if(payeeUser.isPresent() && loggedUser.isPresent()) {
			Payee payee = new Payee();
			payee.setStatus(PayeeStatusUtil.ADD_PENDING);
			payee.setPayeeId((payeeUser.get()).getId());
			payee.setCustId((loggedUser.get()).getId());
			payee = payeeRepository.save(payee);
			
			oTPService.generateOTP((loggedUser.get()).getCustomerId(), payee.getId(), 
					(loggedUser.get()).getEmail(),bodyInit+bodyMiddle+(payeeUser.get()).getId()+bodyFinal+(payeeUser.get()).getFirstName()+bodyFinalAgain);
			
			responseObject.setHttpStatus(HttpStatus.OK);
			responseObject.setData(payeeUser);
			responseObject.setMessage("Transaction Initiated");
			
			return responseObject;
		}
		
		responseObject.setHttpStatus(HttpStatus.BAD_REQUEST);
		responseObject.setData(null);
		responseObject.setMessage("Initiation Failed");
		
		return responseObject;
		
	}

}
