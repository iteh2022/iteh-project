import './App.css';
import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';
import Navbar from './components/Navbar';
import Home from './components/pages/Home';
import Login from './components/pages/Login';
import Services from './components/pages/Services';
import PrijavaIspita from './components/pages/PrijavaIspita';
import OdjavaIspita from './components/pages/OdjavaIspita';
import Footer from './components/Footer';
import React, { useState } from 'react';
import { isLoggedIn } from './Helpers';
import PrijavaPredmeta from './components/PrijavaPredmeta';
import PrijavljeniIspiti from './components/PrijavljeniIspiti';

export const ApiContext = React.createContext();
export const CurrentUserContext = React.createContext(null);

function App() {

  const api = "http://localhost:8080/api";

  const [currentUser, setCurrentUser] = useState(() => isLoggedIn());

  return (
    <>
    <CurrentUserContext.Provider value={{ currentUser, setCurrentUser }}>
            <ApiContext.Provider value={{ api }}>
              <BrowserRouter>
                <Navbar />
                  <Routes>
                    <Route path='/' element= {<Home />} />
                    <Route path="/login" element={currentUser ? <Navigate to='/' /> : <Login />} />
                    <Route path="/services" element={currentUser ? <Services /> : <Navigate to='/login' /> } />
                    <Route path='/services/prijavaispita' element= {<PrijavaIspita />} />
                    <Route path='/services/odjavaispita' element= {<OdjavaIspita />} />
                    <Route path='/services/prijavapredmeta' element= {<PrijavaPredmeta />} />
                    <Route path="/services/ocene" element={<PrijavljeniIspiti/>} />
                  </Routes>
                  <Footer />
              </BrowserRouter>
            </ApiContext.Provider>
        </CurrentUserContext.Provider>
    </>
    
    
  );
  
}


export default App;
