import { Box, Button, Container, Grid, Paper, TextField, Typography } from '@mui/material'
import { Link } from 'react-router-dom'

function RegisterPage () {
  const handleSubmit = (event) => {
    event.preventDefault()
  }

  return (
    <Container maxWidth='sm'>
      <Box
        sx={{
          alignItems: 'center',
          pt: 18
        }}
      >
        <Paper
          elevation={2}
          sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            px: 5,
            py: 5
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
                  id='userrname'
                  label='Username'
                  name='username'
                  autoComplete='username'
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  name='firstName'
                  required
                  fullWidth
                  id='firstName'
                  label='First Name'
                  autoFocus
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  name='lastName'
                  required
                  fullWidth
                  id='lastName'
                  label='Last Name'
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id='email'
                  label='Email Address'
                  name='email'
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
                  autoComplete='new-password'
                />
              </Grid>
            </Grid>
            <Button
              type='reset'
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
            <Link href='#'>
              Already have an account?
            </Link>
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default RegisterPage
