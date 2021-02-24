import { useEffect, useState, useContext } from "react";
import '../components/user/Leaderboard.css';
import { Link } from 'react-router-dom';
import AuthContext from './AuthContext';

function UserSummary({ user }) {

    const auth = useContext(AuthContext);

    // create set state for game users
    const [gameUsers, setGameUsers] = useState([]);

    function sleep(time) {
        return new Promise((resolve) => setTimeout(resolve, time)
        )
    }

    const fetchSummary = () => {
        fetch(`${process.env.REACT_APP_API_URL}/game/games/${user.userId}`
            , {
                headers: {
                    "Accept": "application/json",
                    "Authorization": `Bearer ${auth.user.token}`
                }
            }
        )
            .then(response => response.json())
            .then(data => setGameUsers(data))
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
                    <h1 className="display-4">{user['userName']} User Summary</h1>
                </div>
            </div>
            <table className="table table-dark">
                <thead className="thead-dark">
                    <tr>
                        <th></th>
                        <th scope="col">Game</th>
                        <th scope="col">Attempted</th>
                        <th scope="col">Correct</th>
                    </tr>
                </thead>
                <tbody>
                    {gameUsers.map(users => (
                        <tr key={users.gameCode}>
                            <td></td>
                            <td>{users.gameCode}</td>
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

export default UserSummary;