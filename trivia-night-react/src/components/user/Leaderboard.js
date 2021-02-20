import { useEffect, useState } from "react";

function Leaderboard() {
    const [leaderboard, setLeaderboard] = useState([]);
    const [rank, setRank] = useState(0);

    const fetchLeaderboard = () => {
        fetch('http://localhost:8080/user/leaderboard')
            .then(response => response.json())
            .then(data => setLeaderboard(data))
            .catch(error => console.log(error));
    };

    useEffect(() => {
        fetchLeaderboard();
    }, []);

    return (
        <>
            <h2>Leaderboard</h2>
            <table>
                <thead>
                    <tr>
                        <th scope="col">Rank</th>
                        <th scope="col">Username</th>
                        <th scope="col">Attempts</th>
                        <th scope="col">Correct</th>
                    </tr>
                </thead>
                <tbody>
                    {leaderboard.map(leader => (
                        <tr key={leader.userId}>
                            {<td>{rank}</td>}
                            <td>{leader.userName}</td>
                            <td>{leader.numAnswered}</td>
                            <td>{leader.numCorrect}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </>
    );
}

// {rankings.map(rank => (
//     <td>{rank}</td>
// ))}
export default Leaderboard;