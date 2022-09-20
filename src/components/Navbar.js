import React, {useEffect, useState, useContext} from 'react'
import { Link } from 'react-router-dom'
import Button from './Button';
import { ApiContext, CurrentUserContext } from '../App'
import { logout } from '../Helpers'
import './Navbar.css';
import { useNavigate } from 'react-router-dom'

function Navbar() {

  let navigate = useNavigate();
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);
  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);
  const { currentUser, setCurrentUser } = useContext(CurrentUserContext);

  const showButton = () => {
    if(window.innerWidth <= 960) {
      setButton(false);
    }else{
        setButton(true);
      }
  };
  function handleLogout() {
    logout(); 
    setCurrentUser(null); 
    navigate('/'); 
}

  useEffect(() => {
    showButton()
  }, []);

  window.addEventListener('resize', showButton);

  return (
    <>
        <nav className='navbar'>
            <div className='navbar-container'>
                <Link to="/" className='navbar-logo' onClick={closeMobileMenu}>
                    STUD
                </Link>
                <div className='menu-icon' onClick={handleClick}>
                  <i className={click ? 'fas fa-times' : 'fas fa-bars'} />
                </div>
                <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                  <li className='nav-item'>
                    <Link to ='/' className='nav-links' onClick={closeMobileMenu}>
                      Početna
                    </Link>
                  </li>
                  <li className='nav-item'>
                    <Link to ='/services' className='nav-links' onClick={closeMobileMenu}>
                      Usluge
                    </Link>
                  </li>
                  <li className='nav-item'>
                    <Link to ='/login' className='nav-links-mobile' onClick={closeMobileMenu}>
                      Uloguj se
                    </Link>
                  </li>
                  <li className='nav-item'>
                    <Link to ='/logout' className='nav-links-mobile' onClick={closeMobileMenu}>
                      Odjavi se
                    </Link>
                  </li>
                </ul>
                {button && <Button path='/login' buttonStyle='btn--outline'>Uloguj se</Button>}
                {button && <Button path='/login' buttonStyle='btn--outline' onClick={()=>handleLogout()}>Odjavi se</Button>}
            </div>
        </nav>
    </>

  )
}

export default Navbar