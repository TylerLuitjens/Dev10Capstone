import { useEffect, useState } from "react";
import { Link, useHistory } from 'react-router-dom';
import Errors from './Errors';


function JoinGame({setGame, game}) {

    const [gameCode, setGamecode] = useState('');
    const [errors, setErrors] = useState([]);
    const history = useHistory();
    const dependencies = [];
    dependencies.push(game);

    const handleSubmit = (event) => {     
        setErrors([]);
        event.preventDefault();
        event.stopPropagation();

        let url = "http://localhost:8080/game/" + gameCode;
        
        fetch(url)
            .then(response => response.json())
            .then(data => setGame(data))
            .catch(error => {
                let errs = [];
                errs.push("Invalid Game Code");
                setErrors(errs)
            });
    };

    // We need this in here because React Router is a bit slow in updating the state variables, so we wait for a change to occur, and then navigate to the correct page
    useEffect( () => {
        if (game !== [] && game['gameCode'] === gameCode) {
            history.push("/Game")
        }
        console.log(game);;
    }, [game]);

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
                    <input type="text" id="gameCode" className="form-control bg-light mt-5" name="gameCode" placeholder="Enter the Game Code" onChange={(event) => setGamecode(event.target.value)}/>
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