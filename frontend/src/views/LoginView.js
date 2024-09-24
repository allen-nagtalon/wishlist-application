import { Box, Button, Checkbox, Container, Divider, FormControlLabel, Paper, TextField, Typography } from '@mui/material'
import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import ApiInstance from '../services/ApiInstance'

function LoginView () {
  const [email, setEmail] = useState()
  const [password, setPassword] = useState()

  const navigate = useNavigate()

  const handleSubmit = (event) => {
    event.preventDefault()

    ApiInstance.post('/user/login',
      {
        username: email,
        password: password
      }
    )
      .then((res) => {
        window.localStorage.setItem('access_token', res.data.data.token)
        ApiInstance.defaults.headers.Authorization = `JWT ${window.localStorage.getItem('access_token')}`
      })
      .then(navigate('/'))
  }

  return (
    <Container maxWidth='sm'>
      <Box
        sx={{
          alignItems: 'center',
          mt: 18
        }}
      >
        <Paper
          elevation={2}
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            p: 5
          }}
        >
          <Typography component='h2' variant='h5' sx={{ fontWeight: 600 }}>
            Sign In
          </Typography>
          <Box component='form' onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <TextField
              required
              fullWidth
              id='email'
              label='Email'
              name='email'
              value={email}
              onInput={e => setEmail(e.target.value)}
              autoFocus
            />
            <TextField
              required
              fullWidth
              id='password'
              label='Password'
              type='password'
              name='password'
              value={password}
              onInput={e => setPassword(e.target.value)}
              sx={{ my: 2 }}
            />
            <FormControlLabel
              control={<Checkbox value='remember' color='primary' />}
              label='Remember me'
            />
            <Button
              type='submit'
              fullWidth
              variant='contained'
              sx={{
                mt: 3,
                mb: 2,
                bgcolor: 'accent.main',
                color: 'text.light'
              }}
            >
              Sign In
            </Button>
          </Box>
          <Typography variant='body2' sx={{ my: 2 }}>
            <Link href='#'>
              Forgot password?
            </Link>
          </Typography>
          <Divider variant='middle' flexItem>or</Divider>
          <Typography variant='body2' sx={{ my: 2 }}>
            <Link to='/register'>
              Don't have an account? Sign Up
            </Link>
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default LoginView
