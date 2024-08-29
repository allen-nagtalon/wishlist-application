import { Box, Container, Paper, Typography } from '@mui/material'

function RegisterConfirmPage () {
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
          <Typography variant='h5'>
            Thank you for registering with us!
          </Typography>
          <Typography textAlign='center' sx={{ pt: 3, pb: 5 }}>
            Please visit your email and click on the confirmation link provided to unlock your new account!
          </Typography>
          <Typography component='a' href='/' variant='body2'>
            Return to Home
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default RegisterConfirmPage
