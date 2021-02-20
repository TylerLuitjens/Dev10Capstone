import { useState } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';


function Login({handleSetUser}) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);
  

    const handleSubmit = async (event) => {
        event.preventDefault();
    }

    return(
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">Log In</h1>
                </div>
            </div>
            <Errors errors={errors} />

            <div className="d-flex justify-content-center mt-5">
            <form className="form" onSubmit={handleSubmit}>
                <div className="input-group input-group-lg">
                    <div className = "input-group-prepend">
                        <label htmlFor="userName" className="input-group-text bg-warning text-light mt-5">Username: </label>
                    </div>
                    <input type="text" id="userName" className="form-control bg-light mt-5" name="userName" placeholder="Enter your username" onChange={(event) => setUsername(event.target.value)}/>
                </div>

                <div className="my-4 input-group input-group-lg mt-5">
                    <div className = "input-group-prepend">
                        <label htmlFor="pwd" className="input-group-text bg-warning text-light">Password: </label>
                    </div>
                    <input type="password" id="pwd"  className="form-control bg-light" name="pwd" placeholder="Enter your password" onChange={(event) => setPassword(event.target.value)} />
                </div>
                <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-lg btn-success mr-3 mt-4">Log In</button>
                    <Link to={'/'} className="btn btn-lg btn-info mt-4">Return to Home</Link>
                </div>
            </form>
            </div>
        </>
    )
}

export default Login;