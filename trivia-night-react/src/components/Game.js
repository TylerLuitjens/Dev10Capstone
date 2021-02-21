import { useState } from "react";
import { Link, useHistory, useLocation } from 'react-router-dom';
import Errors from './Errors';


function Game({game}) {
    const [errors, setErrors] = useState([]);
    const history = useHistory();

    const handleSubmit = async (event) => {
        event.preventDefault();
        // TODO update gameUser after completing all of the questions
    }

    return(

        <>
            {console.log(game)}
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h4>{game['gameCode']}</h4>
                </div>
                <div className="d-flex justify-content-center center">
                    <h1 className="display-4">{game['gameQuestions'][0]['question']}</h1>
                </div>
            </div>
            <Errors errors={errors} />

            <div className="d-flex justify-content-center mt-5">
                <button className="button btn-success btn-lg btn-block col-md-8">Hello</button>
            </div>
        </>
    )
}

export default Game;