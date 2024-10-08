import { Box, Button, Container, Grid, Paper, TextField, Typography } from '@mui/material'
import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import ApiInstance from '../services/ApiInstance'

function RegisterView () {
  const [username, setUsername] = useState('')
  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')

  const navigate = useNavigate()

  const handleSubmit = (event) => {
    event.preventDefault()

    ApiInstance.post('/user/register',
      {
        username: username,
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password
      }
    )
      .then((res) => {
        if (res.status === 201) {
          navigate('/register/confirm')
        } else {
          // Handle API errors
        }
      })
      .catch((err) => {
        console.log(err)
      })
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
            Sign Up
          </Typography>
          <Box component='form' onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id='username'
                  label='Username'
                  name='username'
                  value={username}
                  onInput={e => setUsername(e.target.value)}
                  autoComplete='username'
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  name='firstName'
                  required
                  fullWidth
                  id='firstName'
                  label='First Name'
                  value={firstName}
                  onInput={e => setFirstName(e.target.value)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  name='lastName'
                  required
                  fullWidth
                  id='lastName'
                  label='Last Name'
                  value={lastName}
                  onInput={e => setLastName(e.target.value)}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id='email'
                  label='Email Address'
                  name='email'
                  value={email}
                  onInput={e => setEmail(e.target.value)}
                  autoComplete='email'
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  name='password'
                  label='Password'
                  type='password'
                  id='password'
                  value={password}
                  onInput={e => setPassword(e.target.value)}
                  autoComplete='new-password'
                />
              </Grid>
            </Grid>
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
              Sign Up
            </Button>
          </Box>
          <Typography variant='body2' sx={{ mt: 2 }}>
            <Link to='/login'>
              Already have an account?
            </Link>
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default RegisterView
