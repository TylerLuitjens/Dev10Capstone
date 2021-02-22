import { useState, useEffect } from "react";
import { Link, useHistory, useLocation, useParams } from 'react-router-dom';
import Errors from './Errors';
import SelectionMessage from './SelectionMessage';

function Game({ game, user, setGame }) {

    const [errors, setErrors] = useState([]);
    const [currentUser, setCurrentUser] = useState([]);
    const [gameNumAnswered, setGameNumAnswered] = useState(0);
    const [gameNumCorrect, setGameNumCorrect] = useState(0);
    const history = useHistory();
    const [activeIndex, setActiveIndex] = useState(0);
    const [selected, setSelected] = useState(false);
    const [selectedCorrect, setSelectedCorrect] = useState(false);

    // Find the correct GameUser

    const findUser = () => {
        if (currentUser['userId'] !== null && currentUser['userId'] !== user['userId']) {
            game['gameUsers'].forEach(element => {
                if (element['userId'] == user['userId']) {
                    setCurrentUser(element);
                    setGameNumAnswered(element['numAnswered']);
                    setGameNumCorrect(element['numCorrect']);
                    setActiveIndex(element['numAnswered']);
                }
            });
        }
    };

    // Current question is going to be based off of the current number of questions answered by the user

    let question = [];
    question['question'] = "Loading...";

    if (game['gameQuestions'] !== undefined) {
        question = game['gameQuestions'][activeIndex];
    }

    const handleSubmit = () => {
        // TODO set current user's numAnswered and numCorrect
        // TODO update gameUser after completing all of the questions
        let tempUser = [];
        tempUser['gameCode'] = currentUser['gameCode'];
        tempUser['userId'] = currentUser['userId'];
        tempUser['numAnswered'] = gameNumAnswered;
        tempUser['numCorrect'] = gameNumCorrect;

        setGame([]);
        setCurrentUser([]);
        history.push("/user/leaderboard"); // FIXME this will need to go to the summary page instead
    }

    const handleSelection = (event) => {

        console.log("Num answered: " + gameNumAnswered);
        console.log("Num correct: " + gameNumCorrect);

        setActiveIndex(activeIndex + 1);
        setSelected(false);

        if (activeIndex >= 9) {
            handleSubmit();
        }
    }

    const handleChosenAnswer = (event) => {
        if (currentUser['userId'] !== null && currentUser['userId'] !== user['userId']) {
            findUser();
        }

        let index = event.target.id;
        let answerSelected = question['answers'][index];
        
        setSelected(true);
        setGameNumAnswered(gameNumAnswered + 1);
        setSelectedCorrect(answerSelected.correct);

        if (answerSelected.correct) {
            setGameNumCorrect(gameNumCorrect + 1);
        }

    }

    if (game !== [] && game['gameQuestions'] !== undefined && game['gameQuestions'] !== null && currentUser !== []) {
        return(
            <>
                <div className="jumbotron mb-3">
                    <div className="d-flex justify-content-center">
                        <h4>{game['gameCode']}</h4>
                    </div>
                    <div className="d-flex justify-content-center center">
                        <h1 className="display-5">{question['question']}</h1>
                    </div>
                </div>
                <Errors errors={errors} />
                <SelectionMessage selectedCorrect={selectedCorrect} selected={selected} />
    
                <div id="buttons">
                {!selected && (
                <>
                    <div className="d-flex justify-content-center mt-5">
                        <button id="0" className="button btn-info btn-lg btn-block col-md-8" onClick={(event) => handleChosenAnswer(event)} disabled={selected}>{question['answers'][0]['answer']}</button>
                    </div>
                    <div className="d-flex justify-content-center mt-5">
                        <button id="1" className="button btn-info btn-lg btn-block col-md-8" onClick={(event) => handleChosenAnswer(event)} disabled={selected}>{question['answers'][1]['answer']}</button>
                    </div>
                    <div className="d-flex justify-content-center mt-5">
                        <button id="2" className="button btn-info btn-lg btn-block col-md-8" onClick={(event) => handleChosenAnswer(event)} disabled={selected}>{question['answers'][2]['answer']}</button>
                    </div>
                    <div className="d-flex justify-content-center mt-5">
                        <button id="3" className="button btn-info btn-lg btn-block col-md-8" onClick={(event) => handleChosenAnswer(event)} disabled={selected}>{question['answers'][3]['answer']}</button>
                    </div>
                </>
                )}
                </div>
    
                <div className="d-flex justify-content-center mt-5">
                     { selected && (
                    <button className="button btn-warning btn-lg btn-block col-md-4" onClick={ () => handleSelection()} disabled={!selected}>Next</button>
                     )}
                </div>
            </>
        )
    } else {
        return (
            <>
                <div className="jumbotron mb-3">
                    <div className="d-flex justify-content-center center">
                        <h1 className="display-4">Loading...</h1>
                    </div>
                </div>
            </>
        )
    }


}

export default Game;