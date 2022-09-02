
import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Home from './components/pages/Home';
import Services from './components/pages/Services';
import Login from './components/pages/Login';
import Footer from './components/Footer';
import PrijavaIspita from './components/pages/PrijavaIspita';
import PrijavaPredmeta from './components/PrijavaPredmeta';


function App() {
  return (
    <>
    <div className='container'>
    <PrijavaPredmeta />
    </div>
    <Router>
      <Navbar />
      <Routes>
        <Route path='/' element= {<Home />} />
        <Route path='/services' element= {<Services />} />
        <Route path='/login' element= {<Login />} />
        <Route path='/services/prijavaispita' element= {<PrijavaIspita />} />
      </Routes>
      <Footer />
      </Router>
    </>
    
    
  );
  
}


export default App;
