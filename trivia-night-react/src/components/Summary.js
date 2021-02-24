import { useEffect, useState, useContext } from "react";
import '../components/user/Leaderboard.css';
import { Link } from 'react-router-dom';
import AuthContext from './AuthContext';

function Summary({ game, setGame }) {

    const auth = useContext(AuthContext);

    // create set state for game users
    const [gameUsers, setGameUsers] = useState([]);

    function sleep(time) {
        return new Promise((resolve) => setTimeout(resolve, time)
        )
    }

    const fetchSummary = () => {
        fetch(`http://localhost:8080/game/gameusers/${game.gameCode}`
            , {
                headers: {
                    "Accept": "application/json",
                    "Authorization": `Bearer ${auth.user.token}`
                }
            }
        )
            .then(response => response.json())
            .then(data => setGameUsers(data))
            .then(setGame([]))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        sleep(200).then(() => {
            fetchSummary();
        })
    }, []);

    return (
        <>
            <div className="jumbotron mb-3">
                <div className="d-flex justify-content-center">
                    <h1 className="display-4">{game['gameCode']} Summary</h1>
                </div>
            </div>
            <table className="table table-dark">
                <thead className="thead-dark">
                    <tr>
                        <th scope="col">Rank</th>
                        <th scope="col">Username</th>
                        <th scope="col">Attempted</th>
                        <th scope="col">Correct</th>
                    </tr>
                </thead>
                <tbody>
                    {gameUsers.map(users => (
                        <tr key={users.userId}>
                            <td></td>
                            <td>{users.userName}</td>
                            <td>{users.numAnswered}</td>
                            <td>{users.numCorrect}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="d-flex justify-content-center">
                <Link to={'/'} className="btn btn-lg btn-info mt-4">Return to Home</Link>
            </div>
        </>
    );

}

export default Summary;