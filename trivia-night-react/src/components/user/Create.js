import {useState} from 'react';
import Errors from '../Errors';
import {BrowserRouter as Router, useHistory,Link} from 'react-router-dom';

function Create() {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);

    const history = useHistory();


    const handleCreateUser = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/user/create', {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userName,
                    password
                })
            })
            console.log(response.status);

            if (response.status === 201) {
                try {
                    history.push('/')
                } catch (error) {
                    throw new Error('Something went wrong');
                }
            }

            if (response.status === 400) {
                
                if (userName === "") {
                    throw new Error('Error: username is required.');
                }
                if (password === "") {
                    throw new Error('Error: password is required.');
                }
                throw new Error('Error: there is something wrong with your username and password.');
            }
        } catch (err) {
            setErrors([err.message]);
        }

    }



    return(
        <>
            <div className="jumbotron">
                <div className="d-flex justify-content-center">
                    <h1 className="mb-4 display-4">Create User</h1>
                </div>
            </div>
            
            <Errors errors={errors} />
            <div className="d-flex justify-content-center mt-5">
                <form className="form, " onSubmit={handleCreateUser}>
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
                        <button type="submit" className="btn btn-lg btn-success mr-3 mt-4">Create User</button>
                        <Link to={'/login'} className="btn btn-lg btn-info mt-4">Go To Log In</Link>
                    </div>
                </form>
            </div>
        </>
        
    )
}

export default Create;