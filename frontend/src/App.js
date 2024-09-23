import NavBar from './components/NavBar/NavBar'
import Footer from './components/Footer/Footer'
import { Box, createTheme, CssBaseline, ThemeProvider } from '@mui/material'
import { createBrowserRouter, Route, Routes } from 'react-router-dom'
import LandingView from './views/LandingView'
import LoginView from './views/LoginView'
import RegisterView from './views/RegisterView'
import RegisterConfirmView from './views/RegisterConfirmView'
import WishlistListView from './views/WishlistListView'
import WishListView from './views/WishListView'
import { useState } from 'react'

const theme = createTheme({
  palette: {
    light: {
      main: '#FFFFFF'
    },
    dark: {
      main: '#6C584E'
    },
    accent: {
      main: '#A2AF7F',
      dark: '#717D4E'
    },
    text: {
      dark: '#261505',
      light: '#FFFFFF'
    },
    misc: {
      bg1: '#FAF9EF'
    }
  },
  typography: {
    fontFamily: 'Poppins',
    logo: {
      fontSize: '1.4rem',
      fontWeight: 700,
      letterSpacing: '.1rem'
    },
    button: {
      textTransform: 'none'
    }
  }
})

function App () {
  const [user, setUser] = useState(null)

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <NavBar user={user} setUser={setUser} />
      <Box id='navbar-spacing' sx={{ mt: 10 }} />
      <div>
        <Routes>
          <Route path='/' element={<LandingView />} />
          <Route path='/login' element={<LoginView setUser={setUser} />} />
          <Route path='/register' element={<RegisterView />} />
          <Route path='/register/confirm' element={<RegisterView />} />
          <Route path='/wishlists' element={<WishlistListView />} />
          <Route path='/wishlists/:wishlistId' element={<WishListView />} />
        </Routes>
      </div>
      <Box id='footer-spacing' sx={{ mb: '50px' }} />
      <Footer />
    </ThemeProvider>
  )
}

export default App
