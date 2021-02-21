import { useState } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';


function JoinGame({handleSetUser}) {
    const [gameCode, setGamecode] = useState('');
    const [errors, setErrors] = useState([]);
  

    const handleSubmit = async (event) => {
        event.preventDefault();
        // TODO endpoint is localhost:8080/game/{gameCode}
        // response contains all of the game Users, and Questions
    }

    return(
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">Join Game</h1>
                </div>
            </div>
            <Errors errors={errors} />

            <div className="d-flex justify-content-center mt-5">
            <form className="form" onSubmit={handleSubmit}>
                <div className="input-group input-group-lg">
                    <div className = "input-group-prepend">
                        <label htmlFor="gameCode" className="input-group-text bg-warning text-light mt-5">Game Code: </label>
                    </div>
                    <input type="text" id="gameCode" className="form-control bg-light mt-5" name="userName" placeholder="Enter the Game Code" onChange={(event) => setGamecode(event.target.value)}/>
                </div>

                <div className="d-flex justify-content-center">
                    <button type="submit" className="btn btn-lg btn-success mr-3 mt-4">Join</button>
                    <Link to={'/'} className="btn btn-lg btn-info mt-4">Return to Home</Link>
                </div>
            </form>
            </div>
        </>
    )
}

export default JoinGame;