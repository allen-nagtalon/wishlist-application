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
          <Typography>
            Thank you for registering with us!
          </Typography>
          <Typography>
            Please visit your email and click on the confirmation link provided to unlock your new account!
          </Typography>
        </Paper>
      </Box>
    </Container>
  )
}

export default RegisterConfirmPage
