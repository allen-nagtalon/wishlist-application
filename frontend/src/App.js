import NavBar from './components/NavBar/NavBar'
import Footer from './components/Footer/Footer'
import { createTheme, CssBaseline, ThemeProvider } from '@mui/material'
import LandingPage from './views/LandingPage'
import LoginPage from './views/LoginPage'
import RegisterPage from './views/RegisterPage'

const theme = createTheme({
  palette: {
    light: {
      main: '#FFFFFF'
    },
    dark: {
      main: '#6C584E'
    },
    accent: {
      main: '#A2AF7F'
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
      <LoginPage />
    </ThemeProvider>
  )
}

export default App
