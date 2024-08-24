import { Box, Button, Checkbox, Container, FormControlLabel, Grid, TextField, Typography } from '@mui/material'
import { Link } from 'react-router-dom'

function LoginPage () {
  const handleSubmit = (event) => {
    event.preventDefault()
  }

  return (
    <Container maxWidth='sm'>
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          mt: 10
        }}
      >
        <Typography component='h2' variant='h5' sx={{ fontWeight: 600 }}>
          Sign In
        </Typography>
        <Box component='form' onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <TextField
            required
            fullWidth
            id='username'
            label='Username/Email'
            name='username'
            autoFocus
          />
          <TextField
            required
            fullWidth
            id='password'
            label='Password'
            type='password'
            name='password'
            autoFocus
            sx={{ my: 2}}
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
          <Grid container>
            <Grid item xs>
              <Link href='#' variant='body2'>
                Forgot password?
              </Link>
            </Grid>
            <Grid item xs>
              <Link href='#' variant='body2'>
                Don't have an account? Sign Up
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  )
}

export default LoginPage
