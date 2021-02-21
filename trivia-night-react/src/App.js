import Home from "./components/Home";
import Create from "./components/user/Create";
import Login from "./components/Login";
import Leaderboard from "./components/user/Leaderboard";
import { useState } from 'react';

import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from 'react-router-dom';

function NotFound() {
  return (
    <h2>Not Found</h2>
  )
}

function App() {

  const [user, setUser] = useState(null);

  const handleSetUser = (user) => {
    setUser(user);
  }

  const handleLogout = () => {
    setUser(null);
  }

  return (
    <div className="container">
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
                    <Link to={''} className="nav-link" onClick={handleLogout}>Log Out</Link>
                    <a onClick={handleLogout}></a>
                  </li>
              )}
              <li className="nav-item active">
                <Link to={'/user/leaderboard'} className="nav-link">Leaderboard</Link>
              </li>
            </ul>
          </div>
        </nav>

        <Switch>

          <Route exact path="/">
            <Home user={user} />
          </Route>

          <Route path="/login">
            <Login handleSetUser={handleSetUser} />
          </Route>

          <Route path="/user/create">
            <Create />
          </Route>

          <Route path="/user/leaderboard">
            <Leaderboard />
          </Route>

          <Route path="*">
            <NotFound />
          </Route>

        </Switch>

      </Router>
    </div>
  );
}

export default App;
