import { useState, useContext } from 'react';
import AuthContext from '../AuthContext';
import Errors from '../Errors';
import { BrowserRouter as Router, useHistory, Link } from 'react-router-dom';

function Create( {setUser} ) {
    const auth = useContext(AuthContext);

    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errors, setErrors] = useState([]);

    const history = useHistory();


    const handleCreateUser = async (event) => {
        event.preventDefault();

        try {
            const response = await fetch(`${process.env.REACT_APP_API_URL}/user/create`, {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    userName,
                    password
                })
            })

            if (response.status === 201) {
                try {
                    await auth.authenticate(userName, password);
                    history.push('/');
                } catch (err) {
                    throw new Error('Unknown Error');
                }
            }

            if (response.status === 400) {
                response.json().then( (data) => {
                    console.log(data);
                    let newErrors = [];
                    data["body"].forEach(element => {
                        newErrors.push(element);
                    })
                    setErrors(newErrors);
                });
            }

        } catch (err) {
            setErrors([err.message]);
        }
    }


    return (
        <>
            <div className="jumbotron">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">Create User</h1>
                </div>
            </div>

            <Errors errors={errors} />
            <div className="d-flex justify-content-center mt-5">
                <form className="form, " onSubmit={handleCreateUser}>
                    <div className="input-group input-group-lg">
                        <div className="input-group-prepend">
                            <label htmlFor="userName" className="input-group-text bg-warning text-light mt-5">Username: </label>
                        </div>
                        <input type="text" id="userName" className="form-control bg-light mt-5" name="userName" placeholder="Enter your username" onChange={(event) => setUsername(event.target.value)} />
                    </div>

                    <div className="my-4 input-group input-group-lg mt-5">
                        <div className="input-group-prepend">
                            <label htmlFor="pwd" className="input-group-text bg-warning text-light">Password: </label>
                        </div>
                        <input type="password" id="pwd" className="form-control bg-light" name="pwd" placeholder="Enter your password" onChange={(event) => setPassword(event.target.value)} />
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