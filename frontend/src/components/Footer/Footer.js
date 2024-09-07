import { Box, Typography } from '@mui/material'
import * as React from 'react'

const links = ['Privacy Policy', 'Terms of Service', 'Accessibility']

function Footer () {
  return (
    <Box bgcolor='accent.dark' justifyContent='center' sx={{ position: 'absolute', bottom: 0, display: 'flex', height: '50px', width: '100%', px: 15 }}>
      <Typography alignSelf='center' variant='body2' sx={{ flexGrow: 1, fontWeight: 600 }}>
        © 2024 Peachy Code
      </Typography>
      <Box sx={{ display: 'flex' }}>
        {links.map((link) => (
          <Typography component='a' key={link} alignSelf='center' variant='body2' sx={{ px: 3 }}>
            {link}
          </Typography>
        ))}
      </Box>
    </Box>
  )
}

export default Footer
