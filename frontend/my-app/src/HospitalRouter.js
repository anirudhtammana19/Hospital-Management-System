import {Routes,Route} from 'react-router-dom'
import SignupForm from './Component/User/PatientSignUp';
const HospitalRouter=()=>{

    return(
        <>
        <Routes>

        <Route path="/signup" element={<SignupForm/>}/>

        </Routes>
        </>
    );
}
export default HospitalRouter;