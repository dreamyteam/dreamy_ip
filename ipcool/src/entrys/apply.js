import Validate from '../components/validate.js'

$(function(){
	let applyValidate  = new Validate({
		el:"#applyValidate",
		inputBoxs:".input_content",
		btnSubmit:"input[type='submit']"
	})
})