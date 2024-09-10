import NavBar from './components/NavBar/NavBar'
import Footer from './components/Footer/Footer'
import { Box, createTheme, CssBaseline, ThemeProvider } from '@mui/material'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import LandingView from './views/LandingView'
import LoginView from './views/LoginView'
import RegisterView from './views/RegisterView'
import RegisterConfirmView from './views/RegisterConfirmView'
import WishlistListView from './views/WishlistListView'
import WishListView from './views/WishListView'

const router = createBrowserRouter([
  {
    path: '/',
    element: <LandingView />
  },
  {
    path: '/login',
    element: <LoginView />
  },
  {
    path: '/register',
    element: <RegisterView />
  },
  {
    path: '/register/confirm',
    element: <RegisterConfirmView />
  },
  {
    path: '/wishlists',
    element: <WishlistListView />
  },
  {
    path: '/wishlists/:wishlistId',
    element: <WishListView />
  }
])

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
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <NavBar />
      <Box id='navbar-spacing' sx={{ mt: 10 }} />
      <RouterProvider router={router} />
      <Box id='footer-spacing' sx={{ mb: '50px' }} />
      <Footer />
    </ThemeProvider>
  )
}

export default App
