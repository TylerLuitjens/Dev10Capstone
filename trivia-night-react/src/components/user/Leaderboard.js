import { useEffect, useState } from "react";

function Leaderboard() {
    const [leaderboard, setLeaderboard] = useState([]);
    const rankings = [1,2,3,4,5,6,7,8,9,10];

    const fetchLeaderboard = () => {
        fetch('http://localhost:3000/user/leaderboard')
            .then(response => response.json())
            .then(data => setLeaderboard(data))
            .catch(error => console.log(error));
    }

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
                        <th scope="col">Answers</th>
                        <th scope="col">Correct</th>
                    </tr>
                </thead>
                {/* <tbody>
                {leaderboard.map(lb => (
                    <tr key={lb.userId}>
                        {rankings.map(rank => (
                            <td>{rank}</td>
                        ))}
                        <td>{lb.userName}</td>
                        <td>{lb.numAnswered}</td>
                        <td>{lb.numCorrect}</td>
                    </tr>
                ))}
            </tbody> */}
            </table>
        </>
    )

}

export default Leaderboard;