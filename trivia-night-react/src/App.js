import Home from "./components/Home";
import Create from "./components/user/Create";
import Login from "./components/Login";
import Leaderboard from "./components/user/Leaderboard";
import jwt_decode from 'jwt-decode';
import AuthContext from './components/AuthContext';
import { useState } from 'react';
import JoinGame from "./components/JoinGame";
import NewGame from "./components/NewGame";

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  Redirect
} from 'react-router-dom';
import Game from "./components/Game";

function NotFound() {
  return (
    <h2>Not Found</h2>
  )
}

function App() {

  const [user, setUser] = useState(null);
  const [game, setGame] = useState([]);

  // MAY NEED TO CHANGE VARIABLES
  const login = (token) => {
    const { userId, sub: userName, authorities } = jwt_decode(token);
    const roles = authorities.split(','); // used to define multiple roles DOUBLE CHECK IT WORKS
    
    fetch(`http://localhost:8080/user/username/${userName}`, {
      method: 'GET',
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}`
      }
    })
      .then(response => response.json())
      .then((data) => { 
        data.token = token;
        setUser(data);
      });

  }

  const authenticate = async (username, password) => {
    const response = await fetch('http://localhost:8080/authenticate', {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username,
        password
      })
    });

    // GOTTA DOUBLE CHECK RESPONSE STATUSES
    if (response.status === 200) {
      const { jwt_token } = await response.json();
      login(jwt_token);

    } else if (response.status === 403) {
      throw new Error('Bad username or password');
    } else {
      throw new Error('There was a problem logging in...');
    }
  }

  const logout = () => {
    setUser(null);
  }

  const auth = {
    user,
    login,
    authenticate,
    logout
  }

  function Alert() {
    alert("Must log in first");
  }

  return (
    <div className="container">

      <AuthContext.Provider value={auth}>
        <Router>

          <nav className="navbar navbar-expand-lg navbar-dark bg-primary mb-4">
            <Link to={'/'} className="navbar-brand">Trivia Night</Link>
            <div className="collapse navbar-collapse" id="navbarContent">
              <ul className="navbar-nav mr-auto">
                {!user && (
                  <>
                    <li className="nav-item active">
                      <Link to={'/login'} className="nav-link">Log In</Link>
                    </li>
                    <li className="nav-item active">
                      <Link to={'/user/create'} className="nav-link">Create User</Link>
                    </li>
                  </>
                )}
                {user && (
                  <li className="nav-item active">
                    <Link to={''} className="nav-link" onClick={logout}>Log Out</Link>
                  </li>
                )}
                <li className="nav-item active">
                  <Link to={'/user/leaderboard'} className="nav-link">Leaderboard</Link>
                </li>
                <li className="nav-item active">
                  {user ? (
                    <Link to={'/newgame'} className="nav-link">New Game</Link>
                  ) : (
                      <Link to={'/newgame'} className="nav-link" onClick={Alert}>New Game</Link>
                    )}
                </li>
                <li className="nav-item active">
                  {user ? (
                    <Link to={'/joingame'} className="nav-link">Join Game</Link>
                  ) : (
                      <Link to={'/joingame'} className="nav-link" onClick={Alert}>Join Game</Link>
                    )}
                </li>
              </ul>
            </div>
          </nav>

          <Switch>
            <Route exact path="/">
              <Home Alert={Alert}/>
            </Route>

            <Route path="/login">
              <Login />
            </Route>

            <Route path="/user/create">
              <Create setUser={setUser} />
            </Route>

            <Route path="/user/leaderboard">
              <Leaderboard />
            </Route>
            <Route path="/joingame">
              {user ? (
                <JoinGame setGame={setGame} game={game} />
              ) : (
                  <Redirect to="/login" />
                )}
            </Route>

            <Route path="/newgame">
              {user ? (
                <NewGame setGame={setGame} game={game} />
              ) : (
                  <Redirect to="/login" />
                )}
            </Route>

            <Route path="/Game">
              <Game game={game} setGame={setGame} user={user} />
            </Route>

            <Route path="*">
              <NotFound />
            </Route>

            <Route path="*">
              <NotFound />
            </Route>

          </Switch>

        </Router>

      </AuthContext.Provider>
    </div>
  );
}

export default App;
