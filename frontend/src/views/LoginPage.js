import { Box, Button, Checkbox, Container, Divider, FormControlLabel, Grid, Paper, TextField, Typography } from '@mui/material'
import { Link } from 'react-router-dom'

function LoginPage () {
  const handleSubmit = (event) => {
    event.preventDefault()
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
            <Link href='#'>
              Don't have an account? Sign Up
            </Link>
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default LoginPage
